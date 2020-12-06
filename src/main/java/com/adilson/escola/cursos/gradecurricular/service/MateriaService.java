package com.adilson.escola.cursos.gradecurricular.service;

import com.adilson.escola.cursos.gradecurricular.entity.MateriaEntity;
import com.adilson.escola.cursos.gradecurricular.repository.IMateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MateriaService implements IMateriaService{

    @Autowired
    private IMateriaRepository materiaRepository;

    @Override
    public List<MateriaEntity> getAll() {
        return this.materiaRepository.findAll();
    }

    @Override
    public MateriaEntity getById(Long id) {
        try {
            Optional<MateriaEntity> materiaEntityFounded = this.materiaRepository.findById(id);

            if (materiaEntityFounded.isPresent()) {
                return materiaEntityFounded.get();
            }
            return null;
        } catch (Exception ex) {
            return null;
        }

    }

    @Override
    public Boolean create(MateriaEntity materiaEntity) {
        try {
            this.materiaRepository.save(materiaEntity);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean update(MateriaEntity materiaEntity) {
        try {

            Optional<MateriaEntity> materiaEntityFounded = this.materiaRepository.findById(materiaEntity.getId());

            if(materiaEntityFounded.isPresent()) {
                MateriaEntity newMateriaEntity = materiaEntityFounded.get();

                newMateriaEntity.setNome(materiaEntity.getNome());
                newMateriaEntity.setCodigo(materiaEntity.getCodigo());
                newMateriaEntity.setCargaHoraria(materiaEntity.getCargaHoraria());
                newMateriaEntity.setNome(materiaEntity.getNome());
                newMateriaEntity.setFrequencia(materiaEntity.getFrequencia());

                this.materiaRepository.save(newMateriaEntity);

                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        try{
            materiaRepository.deleteById(id);
            return true;
        } catch (Error e) {
            return false;
        }
    }

}
