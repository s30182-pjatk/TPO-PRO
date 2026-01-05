package pl.edu.pja.gden.tpopros30182.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.net.MalformedURLException;
import java.net.URL;

public class ImageURLValidator implements ConstraintValidator<ImageURL, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null || s.isEmpty()) {
            return false;
        }

        try {
            URL url = new URL(s);
            String path = url.getPath().toLowerCase();

            return path.endsWith(".jpg") || path.endsWith(".jpeg") ||
                    path.endsWith(".png") || path.endsWith(".gif") ||
                    path.endsWith(".bmp") || path.endsWith(".webp");
        } catch (MalformedURLException e) {
            return false;
        }
    }
}
