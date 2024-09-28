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

    public List<Recruit> findByTitle(String Title){return recruitRepository.findByTitle(Title);}


}
