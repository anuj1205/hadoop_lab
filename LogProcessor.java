import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class LogProcessor {

    // Mapper Class
    public static class LogMapper
            extends Mapper<Object, Text,
            Text, IntWritable> {

        private final static IntWritable one =
                new IntWritable(1);

        private Text logType = new Text();

        public void map(Object key,
                        Text value,
                        Context context)
                throws IOException, InterruptedException {

            // Convert line into string
            String line = value.toString();

            // Split line into words
            String[] parts = line.split(" ");

            // Check if line is not empty
            if (parts.length > 0) {

                // First word = log type
                logType.set(parts[0]);

                // Emit log type with count 1
                context.write(logType, one);
            }
        }
    }

    // Reducer Class
    public static class LogReducer
            extends Reducer<Text, IntWritable,
            Text, IntWritable> {

        private IntWritable result =
                new IntWritable();

        public void reduce(Text key,
                           Iterable<IntWritable> values,
                           Context context)
                throws IOException, InterruptedException {

            int sum = 0;

            // Add all counts
            for (IntWritable val : values) {
                sum += val.get();
            }

            result.set(sum);

            // Output final count
            context.write(key, result);
        }
    }

    // Main Method
    public static void main(String[] args)
            throws Exception {

        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf,
                "log processor");

        job.setJarByClass(LogProcessor.class);

        job.setMapperClass(LogMapper.class);

        job.setCombinerClass(LogReducer.class);

        job.setReducerClass(LogReducer.class);

        job.setOutputKeyClass(Text.class);

        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job,
                new Path(args[0]));

        FileOutputFormat.setOutputPath(job,
                new Path(args[1]));

        System.exit(job.waitForCompletion(true)
                ? 0 : 1);
    }
}