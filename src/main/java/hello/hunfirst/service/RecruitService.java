package hello.hunfirst.service;


import hello.hunfirst.entity.Recruit;
import hello.hunfirst.repository.RecruitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
@RequiredArgsConstructor
public class RecruitService {

    private final RecruitRepository recruitRepository;

    public Recruit save(Recruit recruit){ return recruitRepository.save(recruit);};

    public List<Recruit> findAll(){return recruitRepository.findAll();};

    public List<Recruit> searchTitleContaining(String title){return recruitRepository.findByTitleContaining(title);};

    public Recruit findById(Long recruitId){
        return recruitRepository.findById(recruitId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 ID입니다. " + recruitId));
    }

    public void deleteById(Long recruitId) {
        recruitRepository.deleteById(recruitId);
    }


}
