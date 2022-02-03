package org.pjp.cag.directive;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This class represents the TITLE directive.
 * @author developer
 *
 */
public final class TitleDirective extends Directive {

    /**
     * The TITLE directive.
     */
    public static final String TITLE = "TITLE";

    private final String title;

    /**
     * @param title The title
     */
    public TitleDirective(String title) {
        super(TITLE);
        this.title = checkNotNull(title, "title cannot be null");
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "TitleDirective [title=" + title + ", getType()=" + getType() + "]";
    }

}
