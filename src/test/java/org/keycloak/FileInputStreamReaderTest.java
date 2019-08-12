package org.keycloak;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class FileInputStreamReaderTest {

    @Test
    public void launchBenchmark() throws Exception {
        Options opt = new OptionsBuilder()
                .include(this.getClass().getName() + ".*")
                .mode (Mode.AverageTime)
                .timeUnit(TimeUnit.MICROSECONDS)
                .warmupTime(TimeValue.seconds(1))
                .warmupIterations(5)
                .measurementTime(TimeValue.seconds(1))
                .measurementIterations(31)
                .threads(1)
                .forks(1)
                .shouldFailOnError(true)
                .shouldDoGC(true)
                .build();

        new Runner(opt).run();
    }


    @State(Scope.Benchmark)
    public static class BenchmarkState {

        @Param({"./src/test/resources/short-secret", "./src/test/resources/long-secret"})
        String path;

        Path key;
        FileInputStreamReader fileInputStreamReader = new FileInputStreamReader();
        MemoryMappedFileReader memoryMappedFileReader = new MemoryMappedFileReader();

        @Setup(Level.Trial)
        public void initialize() throws FileNotFoundException {
            key = Paths.get(path);
            if (!Files.exists(key)) {
                throw new FileNotFoundException("KEY doesn't exist");
            }
        }
    }

    @Benchmark
    public String FileInputStreamPerf(BenchmarkState state) throws IOException {
        return state.fileInputStreamReader.readFile(state.key);
    }

    @Benchmark
    public String MemoryMappedPerf(BenchmarkState state) throws IOException {
        return state.memoryMappedFileReader.readFile(state.key);
    }

}