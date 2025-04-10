package Part1;

import java.util.zip.Deflater;
import java.util.zip.Inflater;
import java.util.Base64;

// Utility class for compressing and decompressing strings
public class CompressionUtil {

    /**
     * Compresses a given string using the Deflater (ZLIB compression) algorithm.
     *
     * We choose Deflater/Inflater because:
     * - They are built into Java, making the solution straightforward and portable.
     * - ZLIB compression is efficient and commonly used for text data.
     *
     * Worst-case time complexity: O(n), where n is the length of the input string in bytes.
     *
     * @param data The original message to compress.
     * @return A Base64-encoded string representing the compressed data.
     */
    public static String compress(String data) {
        try {
            // Convert input string to bytes using UTF-8 encoding
            byte[] input = data.getBytes("UTF-8");

            // Initialize the Deflater for compression
            Deflater deflater = new Deflater();
            deflater.setInput(input);
            deflater.finish(); // Mark the end of the input data

            // Buffer to hold compressed data
            byte[] buffer = new byte[1024];
            int compressedDataLength = deflater.deflate(buffer); // Compress the data
            deflater.end(); // Clean up the compressor

            // Return Base64-encoded string to make the binary compressed data readable as text
            return Base64.getEncoder().encodeToString(
                    java.util.Arrays.copyOf(buffer, compressedDataLength)
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Return null if compression fails
        }
    }

    /**
     * Decompresses a Base64-encoded compressed string back to its original form.
     *
     * @param compressedData The Base64-encoded compressed data.
     * @return The decompressed original string.
     */
    public static String decompress(String compressedData) {
        try {
            // Decode Base64 string back to byte array
            byte[] compressedBytes = Base64.getDecoder().decode(compressedData);

            // Initialize the Inflater for decompression
            Inflater inflater = new Inflater();
            inflater.setInput(compressedBytes);

            // Buffer to hold the decompressed data
            byte[] buffer = new byte[1024];
            int resultLength = inflater.inflate(buffer); // Decompress the data
            inflater.end(); // Clean up the decompressor

            // Convert decompressed bytes back to a UTF-8 string
            return new String(buffer, 0, resultLength, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Return null if decompression fails
        }
    }
}
