package com.eei.beanxml.test;

import java.util.List;

public class RSP
{
	private String hRet;
	
	private SCR sCR;
	private List<SRule> sRules;
	private SPKR sPKR;
	private List<SPKRule> sPKRules;
	
	public SCR getsCR()
	{
		return sCR;
	}
	public void setsCR(SCR sCR)
	{
		this.sCR = sCR;
	}
	public List<SRule> getsRules()
	{
		return sRules;
	}
	public void setsRules(List<SRule> sRules)
	{
		this.sRules = sRules;
	}
	public SPKR getsPKR()
	{
		return sPKR;
	}
	public void setsPKR(SPKR sPKR)
	{
		this.sPKR = sPKR;
	}
	public List<SPKRule> getsPKRules()
	{
		return sPKRules;
	}
	public void setsPKRules(List<SPKRule> sPKRules)
	{
		this.sPKRules = sPKRules;
	}
	public String gethRet()
	{
		return hRet;
	}
	public void sethRet(String hRet)
	{
		this.hRet = hRet;
	}
}
