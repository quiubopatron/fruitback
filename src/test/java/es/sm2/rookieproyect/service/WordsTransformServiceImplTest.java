package es.sm2.rookieproyect.service;

import es.sm2.rookieproyect.exception.RookieProjectException;
import es.sm2.rookieproyect.model.Fruit;
import es.sm2.rookieproyect.model.FruitDto;
import es.sm2.rookieproyect.model.WordsTransformRequest;
import es.sm2.rookieproyect.service.FruitServiceImpl;
import es.sm2.rookieproyect.service.WordsTransformService;
import es.sm2.rookieproyect.service.WordsTransformServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@TestPropertySource(properties = {"custom.word = platano"})
public class WordsTransformServiceImplTest {
    @TestConfiguration
    static class ContextConfiguration {

        @Bean
        public WordsTransformService wordsTransformService(FruitServiceImpl fruitService) {
            return new WordsTransformServiceImpl(fruitService);
        }
    }

    @MockBean
    private FruitServiceImpl fruitService;

    @Autowired
    private WordsTransformService wordsTransformService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void wordTransformShouldReturnFourWords() throws RookieProjectException {
        //given
        WordsTransformRequest wordsTransformRequest = buildWord("a", "b");

        FruitDto fruitDto = new FruitDto();
        fruitDto.setIdFruit(1L);
        fruitDto.setName("test");
        List<FruitDto> fruitDtoList = new ArrayList<>();
        fruitDtoList.add(fruitDto);
        Mockito.when(fruitService.list()).thenReturn(fruitDtoList);

        //when
        String result = wordsTransformService.wordsTransform(wordsTransformRequest);

        //then
        Assert.assertEquals("ABPLATANOTEST", result);
    }

    @Test(expected = RookieProjectException.class)
    public void wordTransformServiceShouldThrowRookieProjectExceptionWhenNoDataInFruitsTable() throws RookieProjectException {

        //given
        WordsTransformRequest wordsTransformRequest = buildWord("a", "b");

        Mockito.when(fruitService.list()).thenReturn(null);

        //when
        wordsTransformService.wordsTransform(wordsTransformRequest);
    }

    @Test(expected = RookieProjectException.class)
    public void wordTransformServiceShouldThrowRookieExceptionWhenNoWord1OrWord2() throws RookieProjectException {

        //given
        WordsTransformRequest wordsTransformRequest = new WordsTransformRequest();

        FruitDto fruitDto = new FruitDto();
        fruitDto.setIdFruit(1L);
        fruitDto.setName("test");
        List<FruitDto> fruitDtoList = new ArrayList<>();
        fruitDtoList.add(fruitDto);
        Mockito.when(fruitService.list()).thenReturn(fruitDtoList);

        //when
        wordsTransformService.wordsTransform(wordsTransformRequest);
    }

    private WordsTransformRequest buildWord(String word1, String word2) {
        WordsTransformRequest wordsTransformRequest = new WordsTransformRequest();
        wordsTransformRequest.setWord1(word1);
        wordsTransformRequest.setWord2(word2);

        return wordsTransformRequest;
    }
}
