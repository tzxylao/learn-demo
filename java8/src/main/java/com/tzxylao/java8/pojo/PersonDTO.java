package com.tzxylao.java8.pojo;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;
import org.springframework.beans.BeanUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author laoliangliang
 * @date 2019/4/4 14:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO implements DTOConvert<PersonDTO, Person> {
    private String name;

    private int age;

    @Delegate
    private List<String> collection;


    private Person per;

    @Override
    public Person convert(PersonDTO o) {
        @Cleanup Person person = new Person();
        BeanUtils.copyProperties(o, person);
        return person;
    }

    public static void main(String[] args) {
        PersonDTO dto = new PersonDTO();
        List<String> list = Lists.newArrayList("1", "2", "3");
        dto.setCollection(list);
        System.out.println(dto.size());
        System.out.println(dto.remove("2"));
        System.out.println(Arrays.toString(dto.toArray(new String[2])));

        dto.convert(dto);
    }

}
