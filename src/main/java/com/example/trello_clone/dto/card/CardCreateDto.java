package com.example.trello_clone.dto.card;

import com.example.trello_clone.dto.base.BaseGenericDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardCreateDto implements BaseGenericDto {
    private String name;
    private Long boardColumnId;
}
