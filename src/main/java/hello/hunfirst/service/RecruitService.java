package hello.hunfirst.service;


import hello.hunfirst.dto.RecruitRequest;
import hello.hunfirst.entity.OwnerMember;
import hello.hunfirst.entity.Recruit;
import hello.hunfirst.repository.RecruitRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
@RequiredArgsConstructor
@Transactional
public class RecruitService {

    private final RecruitRepository recruitRepository;

    public Recruit save(Recruit recruit) {
        return recruitRepository.save(recruit);
    }

    ;

    public List<Recruit> findAll() {
        return recruitRepository.findAll();
    }

    ;

    public List<Recruit> searchTitleContaining(String title) {
        return recruitRepository.findByTitleContaining(title);
    }

    ;

    public Recruit findById(Long recruitId) {
        return recruitRepository.findById(recruitId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다. " + recruitId));
    }

    public void deleteById(Long recruitId) {
        recruitRepository.deleteById(recruitId);
    }

    public void updateRecruit(RecruitRequest recruitRequest) {

        Recruit recruit = recruitRepository.findById(recruitRequest.getRecruitId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다. ID: " + recruitRequest.getRecruitId()));

        // 엔티티 업데이트
        recruit.updateRecruit(
                recruitRequest.getTitle(),
                recruitRequest.getStartDate(),
                recruitRequest.getEndDate(),
                recruitRequest.getContent(),
                recruitRequest.getFavor()
        );
    }

    @Transactional
    public void createRecruit(Recruit recruitRequest, OwnerMember ownerMember) {
        Recruit recruit = new Recruit(
                recruitRequest.getTitle(),
                recruitRequest.getStartDate(),
                recruitRequest.getEndDate(),
                recruitRequest.getContent(),
                recruitRequest.getFavor(),
                ownerMember
        );

        recruitRepository.save(recruit);
        ownerMember.addRecruit(recruit);
    }

}
