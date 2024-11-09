package chaeminu.Spring101.service;

import chaeminu.Spring101.domain.Member;
import chaeminu.Spring101.repository.MemberRepository;
import chaeminu.Spring101.repository.MemoryMemberRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository repository;

    @Test
    void 회원가입() {
        // Given
        Member member = new Member();
        member.setName("최민우");

        // When
        Long saveId = memberService.join(member);

        // Then
        Member findMember = memberService.findMember(saveId).get();
        assertThat(findMember.getName()).isEqualTo(member.getName());
    }

    @Test
    public void 중복회원예외() {
        // Given
        Member member1 = new Member();
        member1.setName("최민우");

        Member member2 = new Member();
        member2.setName("최민우");

        // When
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("Duplicate member name: " + member2.getName());
    }

}