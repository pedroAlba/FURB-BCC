package trabalho01.testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.junit.Test;

import trabalho01.controller.ArquivoController;
import trabalho01.model.SRTErrorException;
import trabalho01.model.SRTWarningException;

public class Testes {

	URL url;
	File file;

	@Test(expected = SRTErrorException.class)
	public void testaErrorFormato1() throws SRTErrorException, SRTWarningException, IOException {

		url = getClass().getResource("ErroFormato1.srt");
		file = new File(url.getPath());
		ArquivoController.analisaArquivo(file);
	}

	@Test(expected = SRTErrorException.class)
	public void testaErrorFormato2() throws SRTErrorException, SRTWarningException, IOException {
		url = getClass().getResource("ErroFormato2.srt");
		file = new File(url.getPath());
		ArquivoController.analisaArquivo(file);
	}
	
	@Test(expected = SRTErrorException.class)
	public void testaErrorFormato3() throws SRTErrorException, SRTWarningException, IOException {
		url = getClass().getResource("ErroFormato3.srt");
		file = new File(url.getPath());
		ArquivoController.analisaArquivo(file);
	}

	@Test(expected = SRTWarningException.class)
	public void testaErrorLegenda1() throws SRTErrorException, SRTWarningException, IOException{
		url = getClass().getResource("ErroLegenda1.srt");
		file = new File(url.getPath());
		ArquivoController.analisaArquivo(file);		
	}

	
	@Test(expected = SRTWarningException.class)
	public void testaErrorLegenda2() throws SRTErrorException, SRTWarningException, IOException{
		url = getClass().getResource("ErroLegenda2.srt");
		file = new File(url.getPath());
		ArquivoController.analisaArquivo(file);		
	}
	
	@Test(expected = SRTWarningException.class)
	public void testaErrorLegenda3() throws SRTErrorException, SRTWarningException, IOException{
		url = getClass().getResource("ErroLegenda3.srt");
		file = new File(url.getPath());
		ArquivoController.analisaArquivo(file);		
	}

	@Test
	public void testaExemploCerto1() throws SRTErrorException, SRTWarningException, IOException{
		url = getClass().getResource("ExemploCerto1.srt");
		file = new File(url.getPath());
		ArquivoController.analisaArquivo(file);		
	}
	
	@Test
	public void testaExemploCerto2() throws SRTErrorException, SRTWarningException, IOException{
		url = getClass().getResource("ExemploCerto2.srt");
		file = new File(url.getPath());
		ArquivoController.analisaArquivo(file);		
	}
	
	@Test
	public void testaExemploCerto3() throws SRTErrorException, SRTWarningException, IOException{
		url = getClass().getResource("ExemploCerto3.srt");
		file = new File(url.getPath());
		ArquivoController.analisaArquivo(file);		
	}
	


}
