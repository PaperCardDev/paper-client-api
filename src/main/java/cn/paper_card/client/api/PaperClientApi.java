package cn.paper_card.client.api;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

@SuppressWarnings("unused")
public interface PaperClientApi {

    @NotNull String getClientId();

    @NotNull String getClientSecret();

    @NotNull String getApiBase();

    @NotNull String getApiBase2();

    /**
     * 计算签名
     *
     * @param ts         秒级时间戳
     * @param paramsJson 参数JSON
     * @return 签名字符串
     */
    @NotNull String calcSign(long ts, @NotNull String paramsJson);

    /**
     * 发送请求，自动填写一些参数，如ts, client_id, sign
     * 自动解析响应
     *
     * @param uri    uri,如：/api/test/sign
     * @param params 请求参数对象
     * @param method 请求方法，GET, POST或其他
     * @return data属性对象
     * @throws PaperResponseError 服务端没有返回OK时抛出此异常
     * @throws IOException        IOException
     */
    @Nullable JsonElement sendRequest(@NotNull String uri, @Nullable JsonObject params, @NotNull String method) throws PaperResponseError, IOException;

    /**
     * 通过WebSocket投递事件
     * @param type 事件类型
     * @param time 发生时间，秒级时间戳
     * @param jsonData json数据，为JsonObject类型
     *
     */
    void postEvent(@NotNull String type, long time, @Nullable JsonElement jsonData, @Nullable String receiverId);

    void postEventAsync(@NotNull String type, long time, @Nullable JsonElement jsonData, @Nullable String receiverId);

    void addEventListener(@NotNull EventListener listener);

    void removeEventListener(@NotNull EventListener listener);

    /**
     * 向指定的地址发起请求，请求体和响应体都是JSON
     * @param method HTTP请求方法，如GET
     * @param uri 请求地址，如/qq/bind
     * @param params 请求体
     * @return 响应体中的data
     * @throws PaperResponseError 响应如果不是OK（应用层）
     * @throws IOException 网络异常
     */
    @Nullable JsonElement requestWithAuth(@NotNull String method, @NotNull String uri, @Nullable JsonObject params) throws PaperResponseError, IOException;

    @Nullable JsonElement getWithAuth(@NotNull String uri) throws PaperResponseError, IOException;

    @Nullable JsonElement postWithAuth(@NotNull String uri) throws PaperResponseError, IOException;
}