package com.misha.alexadiscord.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sx.blah.discord.handle.obj.IUser;

public interface UserRepository extends JpaRepository<IUser,Long> {



}
