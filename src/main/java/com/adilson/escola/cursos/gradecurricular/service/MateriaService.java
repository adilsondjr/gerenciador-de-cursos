package com.adilson.escola.cursos.gradecurricular.service;

import com.adilson.escola.cursos.gradecurricular.entity.MateriaEntity;
import com.adilson.escola.cursos.gradecurricular.exception.MateriaException;
import com.adilson.escola.cursos.gradecurricular.repository.IMateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

            throw new MateriaException("Materia not found!", HttpStatus.NOT_FOUND);
        } catch (MateriaException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new MateriaException("Internal Server Error, please, contact the support!", HttpStatus.INTERNAL_SERVER_ERROR);
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

            throw new MateriaException("Materia not found!", HttpStatus.NOT_FOUND);
        } catch (MateriaException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new MateriaException("Internal Server Error, please, contact the support!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean delete(Long id) {
        try{
            this.getById(id);
            materiaRepository.deleteById(id);
            return true;
        } catch (MateriaException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

}
