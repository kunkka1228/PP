package com.partys.tools;
import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.CheckoutCommand;
import org.eclipse.jgit.api.CreateBranchCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.PullResult;
import org.eclipse.jgit.api.errors.CanceledException;
import org.eclipse.jgit.api.errors.DetachedHeadException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidConfigurationException;
import org.eclipse.jgit.api.errors.InvalidRefNameException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.api.errors.RefAlreadyExistsException;
import org.eclipse.jgit.api.errors.RefNotAdvertisedException;
import org.eclipse.jgit.api.errors.RefNotFoundException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.api.errors.WrongRepositoryStateException;
import org.eclipse.jgit.errors.CheckoutConflictException;

public class UpdateFromGitHub{
	

	public void update(String local,String remote) throws CheckoutConflictException, RefAlreadyExistsException, RefNotFoundException, InvalidRefNameException, org.eclipse.jgit.api.errors.CheckoutConflictException, GitAPIException{
		
		File file = new File(local);
		File gitFile = new File(local+"/.git");
		Git gitResult=null;
		if (gitFile.exists()) {
			try {
				gitResult = Git.open(file);
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			CheckoutCommand cc=gitResult.checkout();

			cc.setCreateBranch(false);
			cc.setName("master");
			cc.setUpstreamMode(CreateBranchCommand.SetupUpstreamMode.TRACK);
			
			cc.call();		
			PullCommand pullCmd=gitResult.pull();
			pullCmd.call();

		}
	}
	
}