package org.optionfactory.room;

public enum RoomType {
    PUBLIC("public"),
    PRIVATE("private"),
    SUBSCRIBED("subscribed"),
    OWNED("owned");
    final String verbose;

    RoomType(String verbose) {
        this.verbose = verbose;
    }
}
