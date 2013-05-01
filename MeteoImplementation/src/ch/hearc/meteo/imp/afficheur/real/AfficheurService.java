
package ch.hearc.meteo.imp.afficheur.real;

import ch.hearc.meteo.imp.afficheur.real.view.JFrameAfficheurService;
import ch.hearc.meteo.imp.afficheur.real.view.JPanelStation;
import ch.hearc.meteo.imp.afficheur.simulateur.moo.AfficheurServiceMOO;
import ch.hearc.meteo.spec.afficheur.AffichageOptions;
import ch.hearc.meteo.spec.afficheur.AfficheurService_I;
import ch.hearc.meteo.spec.meteo.listener.event.MeteoEvent;
import ch.hearc.meteo.spec.reseau.MeteoServiceWrapper_I;

public class AfficheurService implements AfficheurService_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	/**
	 * n = #data to print
	 */
	public AfficheurService(String titre, int n, MeteoServiceWrapper_I meteoServiceRemote)
		{
		System.out.println("1");

		if (jFrameAfficheurService == null)
			{
			jFrameAfficheurService = new JFrameAfficheurService();
			}

		if (meteoServiceRemote != null)
			{
			afficheurServiceMOO = new AfficheurServiceMOO(titre, n, meteoServiceRemote);
			jPanelStation = new JPanelStation(afficheurServiceMOO);
			jFrameAfficheurService.addNewStation(jPanelStation);
			}
		}

	public AfficheurService(AffichageOptions affichageOptions, MeteoServiceWrapper_I meteoService)
		{
		this(affichageOptions.getTitre(), affichageOptions.getN(), meteoService);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void printAltitude(MeteoEvent event)
		{
		afficheurServiceMOO.printAltitude(event);
		jPanelStation.refresh();
		}

	@Override
	public void printTemperature(MeteoEvent event)
		{
		afficheurServiceMOO.printTemperature(event);
		jPanelStation.refresh();
		}

	@Override
	public void printPression(MeteoEvent event)
		{
		afficheurServiceMOO.printPression(event);
		jPanelStation.refresh();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//Tools
	private AfficheurServiceMOO afficheurServiceMOO;
	private JPanelStation jPanelStation;

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	//Tools
	private static JFrameAfficheurService jFrameAfficheurService;

	}
