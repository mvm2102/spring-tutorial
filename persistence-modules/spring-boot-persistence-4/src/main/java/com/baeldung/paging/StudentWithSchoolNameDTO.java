package com.baeldung.paging;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class StudentWithSchoolNameDTO {

    private String id;

    private String firstName;

    private String lastName;

    private String schoolName;

}
