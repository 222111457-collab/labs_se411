
package edu.psu.se411.lab14.model;

import org.springframework.data.annotation.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;

public record Product(
    @Id Long id,

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 2, message = "Name is too short")
    String name,

    String description,

    @DecimalMin(value = "0.0", message = "Price cannot be negative")
    BigDecimal price

) {}
