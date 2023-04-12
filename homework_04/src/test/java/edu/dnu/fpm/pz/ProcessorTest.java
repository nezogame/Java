package edu.dnu.fpm.pz;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ProcessorTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    private Producer mockProducer;
    @Mock
    private Consumer mockConsumer;

    @InjectMocks
    private Processor processor;

    @Captor
    private ArgumentCaptor<String> argumentCaptor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        argumentCaptor = ArgumentCaptor.forClass(String.class);
    }

    @Test
    public void process() {
        //GIVEN
        String expected = "Magic value";
        when(mockProducer.produce()).thenReturn(expected);

        //WHEN
        processor.process();

        //THEN
        verify(mockProducer).produce();
        verify(mockConsumer).consume(argumentCaptor.capture());
        String actual = argumentCaptor.getValue();
        assertEquals(expected, actual);
    }

    @Test
    public void processFailed() {
        //GIVEN
        when(mockProducer.produce()).thenReturn(null);
        exception.expect(IllegalStateException.class);
        //WHEN
        processor.process();
    }
}