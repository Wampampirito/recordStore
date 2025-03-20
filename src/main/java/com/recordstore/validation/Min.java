package com.recordstore.validation;
/**
 * Custom annotation for validating that a number is greater than or equal to a specified value.
 */
public @interface Min {
    /**
     * The value that the number must be greater than or equal to.
     * @return  The minimum value.
     */
    int value();

}
