package br.com.alric.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum CommandCategory {
    NONE,
    FUN("Diversão"),
    SYSTEM("Sistema"),
    MODERATION("Moderação");

    public String exhibitionName;
}
