package cn.paper_card.client.api;

import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class PaperResponseError extends Exception {
    private final @NotNull String ec;
    private final @NotNull String em;

    public PaperResponseError(@NotNull String ec, @NotNull String em) {
        this.ec = ec;
        this.em = em;
    }

    public @NotNull String getEc() {
        return this.ec;
    }

    public @NotNull String getEm() {
        return this.em;
    }

    @Override
    public String getMessage() {
        return "ec: %s, em: %s".formatted(this.ec, this.em);
    }
}
