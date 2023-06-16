package com.LibraryManagementSystem.LMS.DTO.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthorRequestDto {
    String name;
    String mobileNo;
    String email;
}
