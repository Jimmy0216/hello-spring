package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;


public class JapMemberRepository implements MemberRepository{

    private  final EntityManager em;

    public JapMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override //이렇게 하면 jpark insert쿼리 다 만듬
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
//        List<Member> result =  em.createQuery("select m from Member m", Member.class)
//                .getResultList();
//        return result;
//        alt + command + N 하면 합쳐짐
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
