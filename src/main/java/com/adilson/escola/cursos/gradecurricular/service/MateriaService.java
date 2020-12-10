package com.adilson.escola.cursos.gradecurricular.service;

import com.adilson.escola.cursos.gradecurricular.dto.MateriaDto;
import com.adilson.escola.cursos.gradecurricular.entity.MateriaEntity;
import com.adilson.escola.cursos.gradecurricular.exception.MateriaException;
import com.adilson.escola.cursos.gradecurricular.repository.IMateriaRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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
    public List<MateriaDto> getAll() {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(this.materiaRepository.findAll(),new TypeToken<List<MateriaDto>>() {}.getType());
    }

    @Override
    public MateriaDto getById(Long id) {
        try {
            ModelMapper mapper = new ModelMapper();
            Optional<MateriaEntity> materiaEntityFounded = this.materiaRepository.findById(id);

            if (materiaEntityFounded.isPresent()) {
                return mapper.map(materiaEntityFounded.get(), MateriaDto.class);
            }

            throw new MateriaException("Materia not found!", HttpStatus.NOT_FOUND);
        } catch (MateriaException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new MateriaException("Internal Server Error, please, contact the support!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public Boolean create(MateriaDto materiaDto) {
        try {
            ModelMapper mapper = new ModelMapper();
            MateriaEntity materiaEntity = mapper.map(materiaDto, MateriaEntity.class);
            this.materiaRepository.save(materiaEntity);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean update(MateriaDto materiaDto) {
        try {

            Optional<MateriaEntity> materiaEntityFounded = this.materiaRepository.findById(materiaDto.getId());

            if(materiaEntityFounded.isPresent()) {
                ModelMapper mapper = new ModelMapper();

                MateriaEntity newMateriaEntity = mapper.map(materiaDto, MateriaEntity.class);

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
