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
}
