package org.optionfactory.author;

import java.util.List;

public record BootstrapTableAuthorList(int total, int totalNotFiltered, List<Author> rows) {
}
