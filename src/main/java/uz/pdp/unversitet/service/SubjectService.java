package uz.pdp.unversitet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.unversitet.dto.ApiResponse;
import uz.pdp.unversitet.entity.Subject;
import uz.pdp.unversitet.repozitary.SubjectRepository;

import java.util.Optional;

@Service
public class SubjectService {
    private  final SubjectRepository subjectRepository;
    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public ApiResponse addSubject(Subject subject){
        if (subjectRepository.existsByName(subject.getName()))
            return new ApiResponse("Already exist Subject", false);
        Subject subject1 = new Subject();
        subject1.setName(subject.getName());
        subjectRepository.save(subject1);
        return new ApiResponse("Successfully Added", true);
    }

    public ApiResponse editedSubject(Integer id,Subject subject){
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (!optionalSubject.isPresent())
            return new ApiResponse("Subject not found",false);
        if (subjectRepository.existsByName(subject.getName()))
            return new ApiResponse("Already exist Subject", false);
        Subject subject1 = optionalSubject.get();
        subject1.setName(subject.getName());
        subjectRepository.save(subject1);
        return  new ApiResponse("Successfully edited",true);
    }
    public ApiResponse deleteSubject(Integer id){
        Optional<Subject> optionalSubject = subjectRepository.findById(id);

        if (optionalSubject.isPresent()) {
            subjectRepository.deleteById(id);
            return  new ApiResponse("Successfully deleted" ,true);
        }else return new ApiResponse("Subject not found", false);

    }
}
