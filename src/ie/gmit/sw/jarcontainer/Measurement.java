package ie.gmit.sw.jarcontainer;

import java.util.LinkedList;
import java.util.List;

public class Measurement 
{

	private List<Efferent> listCE = new LinkedList<Efferent>();
	private List<Afferent> listCA = new LinkedList<Afferent>();
	private List<Result> result = new LinkedList<Result>();
	private JarSet jarSet;
	
	public Measurement(JarSet jarSet) 
	{
		this.jarSet = jarSet;
	}

	public void getCouplings()
	{
		
		for (int i = 0; i < this.jarSet.size(); i++)
		{
			
			Efferent ef = new Efferent(this.jarSet.get(i), this.jarSet);
			
			this.listCE.add(ef);
			
		}
		
		for (int i = 0; i < this.jarSet.size(); i++)
		{
			
			Afferent af = new Afferent(this.jarSet.get(i), this.listCE);
			
			this.listCA.add(af);
			
		}
		
	}
	
	public void calculateInstabilities()
	{
	
		double ceScore, caScore, instabiltiyScore;
		
		Result instabilityResult;
		
		for(int i = 0; i < jarSet.size(); i++){
			
			Class cls = jarSet.get(i);
			
			ceScore = 0;	
			caScore = 0;
			instabiltiyScore = 0; 
			
			for(int j = 0; j < this.listCE.size(); j++)
			{
				
				Efferent efferent = this.listCE.get(j);
				
				
				
				if(cls.equals(efferent.getCls()))
				{
					ceScore = efferent.getResult();
					
					break;
				}
				
			}
			
			for(int j = 0; j < this.listCA.size(); j++)
			{
				
				Afferent afferent = this.listCA.get(j);
				
				if(cls.equals(afferent.getCls()))
				{
					caScore = afferent.getResult();
					
					//System.out.println("Afferent Score: " + caScore);
					
					break;
				}
				
			}
			
			if(ceScore == 0 && caScore == 0)
			{
				instabiltiyScore = 0;
			}
			else
			{
				instabiltiyScore = ceScore / (ceScore + caScore);
			}
				
			
			instabilityResult = new Result(cls, ceScore, caScore, instabiltiyScore);
			
			result.add(instabilityResult);
			
		}
	}

	public List<Efferent> getListCE()
	{
		return listCE;
	}

	public List<Afferent> getListCA()
	{
		return listCA;
	}

	public List<Result> getResult()
	{
		return result;
	}

}