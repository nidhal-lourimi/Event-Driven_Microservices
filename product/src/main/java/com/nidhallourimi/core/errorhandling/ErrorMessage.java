package com.nidhallourimi.core.errorhandling;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Date;

public record ErrorMessage(@Getter Date timestamp,@Getter String message) {
}
