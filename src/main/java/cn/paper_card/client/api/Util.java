package cn.paper_card.client.api;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;

@SuppressWarnings("unused")
public class Util {

    /**
     * 发送数据
     *
     * @param connection 链接
     * @param data       数据
     * @throws IOException IO异常
     */
    public static void sendData(@NotNull HttpURLConnection connection, @NotNull String data) throws IOException {
        final OutputStream outputStream = connection.getOutputStream();

        final PrintWriter printWriter = new PrintWriter(outputStream, false, StandardCharsets.UTF_8);

        printWriter.print(data);

        if (printWriter.checkError()) {
            printWriter.close();
            try {
                outputStream.close();
            } catch (IOException ignored) {
            }
            throw new IOException("PrintWriter.checkError() return true");
        }

        printWriter.close();
        outputStream.close();
    }

    /**
     * 读取数据
     *
     * @param connection 连接
     * @return 数据内容
     * @throws IOException IOException
     */
    public static @NotNull String readData(@NotNull HttpURLConnection connection) throws IOException {
        final InputStream inputStream = connection.getInputStream();
        final InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String line;

        final StringBuilder sb = new StringBuilder();
        try {
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
                sb.append('\n');
            }
        } catch (IOException e) {
            try {
                close(inputStream, inputStreamReader, bufferedReader);
            } catch (IOException ignored) {
            }
            throw e;
        }

        close(inputStream, inputStreamReader, bufferedReader);

        return sb.toString();
    }

    /**
     * 关闭输入流
     *
     * @param is InputStream
     * @param ir InputStreamReader
     * @param br BufferedReader
     * @throws IOException IOException
     */
    public static void close(@NotNull InputStream is, @NotNull InputStreamReader ir, @NotNull BufferedReader br) throws IOException {
        IOException exception = null;
        try {
            br.close();
        } catch (IOException e) {
            exception = e;
        }

        try {
            ir.close();
        } catch (IOException e) {
            exception = e;
        }

        try {
            is.close();
        } catch (IOException e) {
            exception = e;
        }
        if (exception != null) throw exception;
    }

}
