/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package warehouse.exam.demo.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warehouse.exam.demo.model.TbSYSSEQUENCE;
import warehouse.exam.demo.reponsitory.SysSequenceRepository;

/**
 *
 * @author LAPTOP123
 */
@Service
public class SysSequenceService {

    private final SysSequenceRepository sequenceRepository;

    @Autowired
    public SysSequenceService(SysSequenceRepository sequenceRepository) {
        this.sequenceRepository = sequenceRepository;
    }

    public TbSYSSEQUENCE getSEQUENCE(String sq) {
        return sequenceRepository.findBySeqname(sq);
    }

    public void add(TbSYSSEQUENCE sequence) {
        sequenceRepository.save(sequence);
    }

    public void update(TbSYSSEQUENCE sequence) {
        TbSYSSEQUENCE seq = sequenceRepository.findBySeqname(sequence.getSeqname());
        if (seq != null) {
            seq.setSeqvalue(sequence.getSeqvalue() + 1);
            sequenceRepository.save(seq);
        } else {
            throw new RuntimeException("Sequence not found");
        }
    }
}
