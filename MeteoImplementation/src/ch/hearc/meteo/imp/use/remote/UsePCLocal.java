
package ch.hearc.meteo.imp.use.remote;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Properties;

import ch.hearc.meteo.imp.reseau.AfficheurManager;
import ch.hearc.meteo.spec.afficheur.AffichageOptions;
import ch.hearc.meteo.spec.meteo.MeteoServiceOptions;

import com.bilat.tools.reseau.rmi.RmiTools;
import com.bilat.tools.reseau.rmi.RmiURL;

public class UsePCLocal
	{

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static void main(String[] args)
		{
		main();
		}

	public static void main()
		{
		/*try
			{
			saveProperties();
			}
		catch (IOException e)
			{
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		*/
		try
			{
			FileInputStream fis = new FileInputStream(FILE_PROPERTIES);
			BufferedInputStream bis = new BufferedInputStream(fis);
			Properties propertie = new Properties();
			propertie.load(bis);

			String stringIP = propertie.getProperty(ADRESSE_IP);

			InetAddress ip = InetAddress.getByName(stringIP);
			int rmi_port = Integer.valueOf(propertie.getProperty(RMI_PORT));

			bis.close();
			fis.close();

			RmiURL rmiUrl = new RmiURL(AfficheurManager.RMI_ID, ip, rmi_port);

			String portCom = new String("COM1");
			int rand = (int)(Math.random() * 100);
			MeteoServiceOptions meteoServiceOptions = new MeteoServiceOptions(100 + rand, 200 + rand, 300 + rand, 400 + rand);
			AffichageOptions affichageOptions = new AffichageOptions(30, "test - " + String.valueOf(rand));

			new PCLocal(meteoServiceOptions, portCom, affichageOptions, rmiUrl).run();
			}
		catch (Exception e)
			{
			e.printStackTrace();
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	private static void saveProperties() throws IOException
		{
		FileOutputStream fos = new FileOutputStream(FILE_PROPERTIES);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		Properties propertie = new Properties();

		propertie.setProperty(ADRESSE_IP, "127.0.0.1");
		propertie.setProperty(RMI_PORT, "" + RmiTools.PORT_RMI_DEFAUT);
		propertie.store(bos, STORE_NAME);

		bos.close();
		fos.close();
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	private static final String STORE_NAME = "Ni66A - Station M�t�o - Options";
	private static final String ADRESSE_IP = "ADRESSE_IP";
	private static final String RMI_PORT = "RMI_PORT";
	private static final String FILE_PROPERTIES = "settings.ini";

	}
