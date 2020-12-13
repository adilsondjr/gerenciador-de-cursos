package com.adilson.escola.cursos.gradecurricular.service;

import com.adilson.escola.cursos.gradecurricular.dto.MateriaDto;
import com.adilson.escola.cursos.gradecurricular.entity.MateriaEntity;
import com.adilson.escola.cursos.gradecurricular.exception.MateriaException;
import com.adilson.escola.cursos.gradecurricular.repository.IMateriaRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@CacheConfig(cacheNames = "materia")
@Service
public class MateriaService implements IMateriaService{

	private static final String MATERIA_NOT_FOUND = "Materia not found!";
	private static final String INTERNAL_ERROR = "Internal Server Error, please, contact the support!";
	
    @Autowired
    private IMateriaRepository materiaRepository;
    private ModelMapper modelMapper;
    
    public MateriaService(IMateriaRepository materiaRepository) {
		this.modelMapper = new ModelMapper();
		this.materiaRepository = materiaRepository;
	}

    @CachePut(unless = "#result.size()<3")
    @Override
    public List<MateriaDto> getAll() {
        return this.modelMapper.map(this.materiaRepository.findAll(),new TypeToken<List<MateriaDto>>() {}.getType());
    }

    @CachePut(key = "#id")
    @Override
    public MateriaDto getById(Long id) {
        try {
            Optional<MateriaEntity> materiaEntityFounded = this.materiaRepository.findById(id);

            if (materiaEntityFounded.isPresent()) {
                return this.modelMapper.map(materiaEntityFounded.get(), MateriaDto.class);
            }

            throw new MateriaException(MATERIA_NOT_FOUND, HttpStatus.NOT_FOUND);
        } catch (MateriaException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new MateriaException(INTERNAL_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public Boolean create(MateriaDto materiaDto) {
        try {
            MateriaEntity materiaEntity = this.modelMapper.map(materiaDto, MateriaEntity.class);
            this.materiaRepository.save(materiaEntity);
            return Boolean.TRUE;
        }catch (Exception e) {
        	throw new MateriaException(INTERNAL_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Boolean update(MateriaDto materiaDto) {
        try {
    		this.getById(materiaDto.getId());
            MateriaEntity newMateriaEntity = this.modelMapper.map(materiaDto, MateriaEntity.class);

            this.materiaRepository.save(newMateriaEntity);

            return Boolean.TRUE;
        } catch (MateriaException ex) {
            throw ex;
        } catch (Exception ex) {
        	throw new MateriaException(INTERNAL_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean delete(Long id) {
        try{
            this.getById(id);
            materiaRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (MateriaException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

}
