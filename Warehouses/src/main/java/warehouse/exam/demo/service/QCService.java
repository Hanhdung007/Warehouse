package warehouse.exam.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warehouse.exam.demo.DAL.itemmasterDAO;
import warehouse.exam.demo.model.Itemmasters;
import warehouse.exam.demo.reponsitory.ItemmasterRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QCService {

    @Autowired
    ItemmasterRepository imMasterRepositoty;

    public List<itemmasterDAO> getAll() {
        List<itemmasterDAO> dao = new ArrayList<>();
        List<Itemmasters> list = imMasterRepositoty.findAll();
        for (Itemmasters im : list) {
            itemmasterDAO imtDao = new itemmasterDAO();
            imtDao.setDateImport(im.getDateImport());
            imtDao.setId(im.getId());
            imtDao.setItemName(im.getCodeItemdata().getName());
            imtDao.setNote(im.getNote());
            imtDao.setQcAcceptQuantity(im.getQcAcceptQuantity());
            imtDao.setQcInjectQuantity(im.getQcInjectQuantity());
            imtDao.setQcBy(im.getQcBy());
            imtDao.setQuantity(im.getQuantity());
            imtDao.setRecieveNo(im.getRecieveNo());
            dao.add(imtDao);
        }
        return dao;
    }

    public List<itemmasterDAO> searchItem(String keyword) {
        return imMasterRepositoty.searchAllItem(keyword);
    }

    public void AcceptQuantity(int id, int quantityInput, String qcBy) {
        Optional<Itemmasters> itemMaster = imMasterRepositoty.findById(id);
        if (itemMaster.isPresent()) {
            Itemmasters newItem = itemMaster.get();
            Double quantityAccept = newItem.getQcAcceptQuantity() + quantityInput;
            boolean newPass = true;

            newItem.setQcAcceptQuantity(quantityAccept);
            newItem.setPass(newPass);
            newItem.setQcBy(qcBy);
            imMasterRepositoty.save(newItem);
        }
    }

    public void InjectQuantity(int id, int quantityInput, String qcBy) {
        Optional<Itemmasters> itemmasters = imMasterRepositoty.findById(id);
        if (itemmasters.isPresent()) {
            Itemmasters item = itemmasters.get();
            Double quantityInject = item.getQcInjectQuantity() + quantityInput;
            boolean newPass = false;

            item.setQcInjectQuantity(quantityInject);
            item.setPass(newPass);
            item.setQcBy(qcBy);
            imMasterRepositoty.save(item);
        }
    }
}
