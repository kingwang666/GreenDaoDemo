package com.wang.interviewassistant.util;

import android.support.annotation.NonNull;
import android.text.Editable;

/**
 * Base Validator class to either implement or inherit from for custom validation
 */
public abstract class METValidator {

  /**
   * Error message that the view will display if validation fails.
   * <p/>
   * This is protected, so you can change this dynamically in your {@link #isValid(Editable, boolean)}
   * implementation. If necessary, you can also interact with this via its getter and setter.
   */
  protected String errorMessage;

  public METValidator(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public void setErrorMessage(@NonNull String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String getErrorMessage() {
    return this.errorMessage;
  }

  /**
   * Abstract method to implement your own validation checking.
   *
   * @param text    The CharSequence representation of the text in the EditText field. Cannot be null, but may be empty.
   * @param isEmpty Boolean indicating whether or not the text param is empty
   * @return True if valid, false if not
   */
  public abstract boolean isValid(@NonNull Editable text, boolean isEmpty);

}
