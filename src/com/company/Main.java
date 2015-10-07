package com.company;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumSet;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class Main {
    private static final int BLOCK_SIZE = 8192;
    private static final long FILE_SIZE_IN_GB = 2;
    private static final long NUMBER_OF_BLOCKS = 131072 * FILE_SIZE_IN_GB;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Running disk test....");
        long startTime = System.currentTimeMillis();

        //Thread.sleep(2000);
        writeToFile();

        long endTime = System.currentTimeMillis();

        System.out.println("Created a "+ FILE_SIZE_IN_GB + " GB file in " + (endTime - startTime) + "ms");
        System.out.println("Done");
    }

    private static void writeToFile() {
        try {
            Path file = Paths.get(System.getProperty("user.dir"), "disk_test_file_" + FILE_SIZE_IN_GB + "GB");
            SeekableByteChannel output = Files.newByteChannel(file, EnumSet.of(CREATE, APPEND));


            for (long i = 0; i < NUMBER_OF_BLOCKS; i++) {
                ByteBuffer buffer = ByteBuffer.allocate(BLOCK_SIZE);
                output.write(buffer);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
