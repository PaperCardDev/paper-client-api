package cn.paper_card.client.api;

import com.google.gson.JsonElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unused")
public interface PaperEvent {
    long getTime();

    @NotNull String getType();

    @NotNull String getSenderId();

    @Nullable String getReceiverId();

    @Nullable JsonElement getData();

}
