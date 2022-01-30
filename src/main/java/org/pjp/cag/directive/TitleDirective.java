package org.pjp.cag.directive;

import com.google.common.base.Preconditions;

public final class TitleDirective extends Directive {

    /**
     * The TITLE directive.
     */
    public static final String TITLE = "TITLE";

    private final String title;

    public TitleDirective(String title) {
        super(TITLE);
        this.title = Preconditions.checkNotNull(title, "title cannot be null");
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "TitleDirective [title=" + title + ", getType()=" + getType() + "]";
    }

}
