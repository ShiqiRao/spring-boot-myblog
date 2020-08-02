package com.example.myblog;

import com.example.myblog.entity.User;
import com.example.myblog.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ArticleRepositoryTests {

    @Autowired
    UserRepository userRepository;

    @Test
    public void saveAUser_thenFindIt() {
        //保存一条用户数据，然后进行查询
        User leili = new User().setLogin("leili").setFirstName("Lei").setLastName("Li");
        userRepository.save(leili);
        User found = userRepository.findById(leili.getId()).orElse(null);
        assertThat(found).isNotEqualTo(null);
        assertThat(leili).isEqualTo(found);
    }

    @Test
    public void saveUserList_thenCountThem() {
        //保存一个用户数据集合，然后查询记录的数据量
        User leili = new User().setLogin("leili").setFirstName("Lei").setLastName("Li");
        User meimeihan = new User().setLogin("meimeihan").setFirstName("Meimei")
                .setLastName("Han");
        User taolin = new User().setLogin("taolin").setFirstName("Tao").setLastName("Lin");
        User jimgreen = new User().setLogin("jimgreen").setFirstName("Jim").setLastName("Green");
        List<User> toSave = Arrays.asList(leili, meimeihan, taolin, jimgreen);
        userRepository.saveAll(toSave);
        Long countThem = userRepository.count();
        assertThat(countThem).isEqualTo(4);
    }

    @Test
    public void saveAUser_thenDeleteIt() {
        //保存一条用户数据，然后删除它
        User leili = new User().setLogin("leili").setFirstName("Lei").setLastName("Li");
        userRepository.save(leili);
        userRepository.delete(leili);
        User found = userRepository.findById(leili.getId()).orElse(null);
        assertThat(found).isEqualTo(null);
    }

    @Test
    public void saveUserList_thenDeleteThem() {
        //保存一个用户数据集合，然后删除所有记录
        User leili = new User().setLogin("leili").setFirstName("Lei").setLastName("Li");
        User meimeihan = new User().setLogin("meimeihan").setFirstName("Meimei").setLastName("Han");
        User taolin = new User().setLogin("taolin").setFirstName("Tao").setLastName("Lin");
        User jimgreen = new User().setLogin("jimgreen").setFirstName("Jim").setLastName("Green");
        List<User> toSave = Arrays.asList(leili, meimeihan, taolin, jimgreen);
        userRepository.saveAll(toSave);
        userRepository.deleteAll();
        Long countThem = userRepository.count();
        assertThat(countThem).isEqualTo(0);
    }
}
