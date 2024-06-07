package cn.paper_card.client.api;

import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public interface EventListener {
    void on(@NotNull PaperEvent event);
}
