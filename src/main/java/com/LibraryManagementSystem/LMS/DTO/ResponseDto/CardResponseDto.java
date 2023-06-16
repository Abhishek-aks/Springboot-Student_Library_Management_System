package com.LibraryManagementSystem.LMS.DTO.ResponseDto;

import com.LibraryManagementSystem.LMS.Enum.CardStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CardResponseDto {
    private int id;
    private LocalDate issuedDate;
    private LocalDate validTill;
    private CardStatus status;

}
