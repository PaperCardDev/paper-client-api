package cn.paper_card.client.api;

import org.jetbrains.annotations.NotNull;

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
}
