//package simple.example.windows;
//
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.security.KeyStore;
//import java.security.KeyStoreException;
//import java.security.NoSuchAlgorithmException;
//import java.security.PrivateKey;
//import java.security.UnrecoverableKeyException;
//import java.security.cert.Certificate;
//import java.security.cert.X509Certificate;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import br.gov.frameworkdemoiselle.certificate.keystore.loader.factory.KeyStoreLoaderFactory;
//import br.gov.frameworkdemoiselle.certificate.signer.factory.PKCS7Factory;
//import br.gov.frameworkdemoiselle.certificate.signer.pkcs7.PKCS7Signer;
//import br.gov.frameworkdemoiselle.policy.engine.factory.PolicyFactory.Policies;
//
//public class CertificateSignerWin {
//
//	private static final Logger logger = LoggerFactory.getLogger(CertificateSignerWin.class);
//
//	public static void main(String[] args) throws KeyStoreException {
//
//		String PIN = "";
//		Certificate[] certificates = null;
//		/* Obtendo a chave privada */
//
//		String alias;
//		try {
//			logger.info("-------- Fabrica do certificate --------");
//			KeyStore keyStore = KeyStoreLoaderFactory.factoryKeyStoreLoader().getKeyStore();
//			alias = (String) keyStore.aliases().nextElement();
//			logger.info("alias ...........: {}", alias);
//			PrivateKey privateKey = (PrivateKey) keyStore.getKey(alias, PIN.toCharArray());
//			logger.info("privateKey ......: {}", privateKey);
//
//			X509Certificate c = (X509Certificate) keyStore.getCertificate(alias);
//
//			byte[] content = "SERPRO".getBytes();
//
//			/* Parametrizando o objeto doSign */
//			PKCS7Signer signer = PKCS7Factory.getInstance().factoryDefault();
//			signer.setCertificates(keyStore.getCertificateChain(alias));
//			signer.setPrivateKey((PrivateKey) keyStore.getKey(alias, null));
//			signer.setSignaturePolicy(Policies.AD_RT_CADES_2_1);
//			signer.setAttached(true);
//			
//			/* Realiza a assinatura do conteudo */
//			logger.info("Efetuando a  assinatura do conteudo");
//			byte[] signed = signer.doSign(content);
//			
//			/* Valida o conteudo */
//			logger.info("Efetuando a validacao da assinatura.");
//			boolean checked = signer.check(content, signed);
//
//			if (checked) {
//				logger.info("A assinatura foi validada.");
//			} else {
//				logger.info("A assinatura foi invalidada!");
//			}
//			
//			/* Exportando a assintatura */
//			logger.info("Exportando a assintatura.");
//			File file = new File("assinatura.p7s"); //Criamos um nome para o arquivo  
//			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file)); //Criamos o arquivo  
//			bos.write(signed); //Gravamos os bytes lá  
//			bos.close(); //Fechamos o stream.  
//
//		} catch (UnrecoverableKeyException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//}
