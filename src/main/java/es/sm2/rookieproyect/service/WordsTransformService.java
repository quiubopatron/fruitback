package es.sm2.rookieproyect.service;

import es.sm2.rookieproyect.exception.RookieProjectException;
import es.sm2.rookieproyect.model.WordsTransformRequest;
import org.springframework.stereotype.Service;

@Service
public interface WordsTransformService {

    String wordsTransform(WordsTransformRequest wordsTransformRequest) throws RookieProjectException;

}
