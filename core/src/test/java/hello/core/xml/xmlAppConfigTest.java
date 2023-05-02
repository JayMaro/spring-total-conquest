package hello.core.xml;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class xmlAppConfigTest {

    ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");

    @Test
    void xmlAppConfigMemberRepositoryTest() {
        MemberRepository bean = ac.getBean(MemberRepository.class);
        Assertions.assertThat(bean).isInstanceOf(MemoryMemberRepository.class);
    }
}
