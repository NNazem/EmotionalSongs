package com.example.emotionalsongback.service.impl;

import com.example.emotionalsongback.dto.EmozioneDto;
import com.example.emotionalsongback.entity.Canzone;
import com.example.emotionalsongback.entity.Emozione;
import com.example.emotionalsongback.exception.APIException;
import com.example.emotionalsongback.repository.CanzoneRepository;
import com.example.emotionalsongback.service.EmozioneService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class EmozioneServiceImpl implements EmozioneService {

    private CanzoneRepository canzoneRepository;
    private ModelMapper modelMapper;
    @Override
    public String addEmozione(Long canzoneId, EmozioneDto emozioneDto) {
        Emozione emozione = new Emozione();
        emozione.setTipoEmozione(emozioneDto.getTipoEmozione());
        emozione.setVoto(emozioneDto.getVoto());
        emozione.setDescrizione(emozioneDto.getDescrizione());
        Canzone canzone = canzoneRepository.findById(canzoneId).orElseThrow(
                () -> new APIException(HttpStatus.NOT_FOUND, "Canzone non trovata con id: " + canzoneId));

        Set<Emozione> emozioni = canzone.getEmozioni();

        for(Emozione emozionetmp: emozioni){
            if(emozionetmp.getTipoEmozione() == emozione.getTipoEmozione()){
                throw new APIException(HttpStatus.CONFLICT, "L'emozione " + emozione.getTipoEmozione() + " è  già associata alla canzone con id: " + canzoneId);
            }
        }

        emozioni.add(emozione);
        canzoneRepository.save(canzone);

        return "Emozione " + emozione.getTipoEmozione() + " associata correttamente.";
    }

    @Override
    public Set<Emozione> getEmozioni(Long canzoneId) {
        Canzone canzone = canzoneRepository.findById(canzoneId).orElseThrow(
                () -> new APIException(HttpStatus.NOT_FOUND, "Canzone non trovata con id: " + canzoneId));

        Set<Emozione> emozioni = canzone.getEmozioni();

        if(emozioni.isEmpty()){
            throw new APIException(HttpStatus.NOT_FOUND, "Non vi sono emozioni associate alla canzone con id: " + canzoneId);
        }

        return emozioni;
    }

    @Override
    public String deleteEmozione(Long canzoneId, Long emozioneId) {

        Canzone canzone = canzoneRepository.findById(canzoneId).orElseThrow(
                () -> new APIException(HttpStatus.NOT_FOUND, "Canzone non trovata con id: " + canzoneId));

        Set<Emozione> emozioni = canzone.getEmozioni();

        for(Emozione emozionetmp: emozioni){
            if(emozionetmp.getId() == emozioneId){
                emozioni.remove(emozionetmp);
                canzoneRepository.save(canzone);
                return "Emozione " + emozionetmp.getTipoEmozione() + " rimossa correttamente.";
            }
        }


        throw new APIException(HttpStatus.NOT_FOUND, "L'emozione non è associata alla canzone: " + canzoneId);
    }


}
