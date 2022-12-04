package Share.Builtin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class BuiltinPrinter {
	public static String builtinS = """
				.text
				.file	"builtin.c"
				.globl	__malloc                # -- Begin function __malloc
				.p2align	2
				.type	__malloc,@function
			__malloc:                               # @__malloc
			# %bb.0:
				tail	malloc
			.Lfunc_end0:
				.size	__malloc, .Lfunc_end0-__malloc
			                                        # -- End function
				.globl	__str_plus              # -- Begin function __str_plus
				.p2align	2
				.type	__str_plus,@function
			__str_plus:                             # @__str_plus
			# %bb.0:
				addi	sp, sp, -16
				sw	ra, 12(sp)
				sw	s0, 8(sp)
				sw	s1, 4(sp)
				sw	s2, 0(sp)
				mv	s2, a1
				mv	s1, a0
				call	strlen
				mv	s0, a0
				mv	a0, s2
				call	strlen
				add	a0, s0, a0
				addi	a0, a0, 1
				call	malloc
				mv	s0, a0
				mv	a1, s1
				call	strcpy
				mv	a0, s0
				mv	a1, s2
				lw	s2, 0(sp)
				lw	s1, 4(sp)
				lw	s0, 8(sp)
				lw	ra, 12(sp)
				addi	sp, sp, 16
				tail	strcat
			.Lfunc_end1:
				.size	__str_plus, .Lfunc_end1-__str_plus
			                                        # -- End function
				.globl	__str_eq                # -- Begin function __str_eq
				.p2align	2
				.type	__str_eq,@function
			__str_eq:                               # @__str_eq
			# %bb.0:
				addi	sp, sp, -16
				sw	ra, 12(sp)
				call	strcmp
				seqz	a0, a0
				lw	ra, 12(sp)
				addi	sp, sp, 16
				ret
			.Lfunc_end2:
				.size	__str_eq, .Lfunc_end2-__str_eq
			                                        # -- End function
				.globl	__str_ne                # -- Begin function __str_ne
				.p2align	2
				.type	__str_ne,@function
			__str_ne:                               # @__str_ne
			# %bb.0:
				addi	sp, sp, -16
				sw	ra, 12(sp)
				call	strcmp
				snez	a0, a0
				lw	ra, 12(sp)
				addi	sp, sp, 16
				ret
			.Lfunc_end3:
				.size	__str_ne, .Lfunc_end3-__str_ne
			                                        # -- End function
				.globl	__str_lt                # -- Begin function __str_lt
				.p2align	2
				.type	__str_lt,@function
			__str_lt:                               # @__str_lt
			# %bb.0:
				addi	sp, sp, -16
				sw	ra, 12(sp)
				call	strcmp
				srli	a0, a0, 31
				lw	ra, 12(sp)
				addi	sp, sp, 16
				ret
			.Lfunc_end4:
				.size	__str_lt, .Lfunc_end4-__str_lt
			                                        # -- End function
				.globl	__str_le                # -- Begin function __str_le
				.p2align	2
				.type	__str_le,@function
			__str_le:                               # @__str_le
			# %bb.0:
				addi	sp, sp, -16
				sw	ra, 12(sp)
				call	strcmp
				slti	a0, a0, 1
				lw	ra, 12(sp)
				addi	sp, sp, 16
				ret
			.Lfunc_end5:
				.size	__str_le, .Lfunc_end5-__str_le
			                                        # -- End function
				.globl	__str_gt                # -- Begin function __str_gt
				.p2align	2
				.type	__str_gt,@function
			__str_gt:                               # @__str_gt
			# %bb.0:
				addi	sp, sp, -16
				sw	ra, 12(sp)
				call	strcmp
				sgtz	a0, a0
				lw	ra, 12(sp)
				addi	sp, sp, 16
				ret
			.Lfunc_end6:
				.size	__str_gt, .Lfunc_end6-__str_gt
			                                        # -- End function
				.globl	__str_ge                # -- Begin function __str_ge
				.p2align	2
				.type	__str_ge,@function
			__str_ge:                               # @__str_ge
			# %bb.0:
				addi	sp, sp, -16
				sw	ra, 12(sp)
				call	strcmp
				not	a0, a0
				srli	a0, a0, 31
				lw	ra, 12(sp)
				addi	sp, sp, 16
				ret
			.Lfunc_end7:
				.size	__str_ge, .Lfunc_end7-__str_ge
			                                        # -- End function
				.globl	__str_length            # -- Begin function __str_length
				.p2align	2
				.type	__str_length,@function
			__str_length:                           # @__str_length
			# %bb.0:
				tail	strlen
			.Lfunc_end8:
				.size	__str_length, .Lfunc_end8-__str_length
			                                        # -- End function
				.globl	__str_substring         # -- Begin function __str_substring
				.p2align	2
				.type	__str_substring,@function
			__str_substring:                        # @__str_substring
			# %bb.0:
				addi	sp, sp, -32
				sw	ra, 28(sp)
				sw	s0, 24(sp)
				sw	s1, 20(sp)
				sw	s2, 16(sp)
				sw	s3, 12(sp)
				mv	s3, a1
				mv	s2, a0
				sub	s1, a2, a1
				addi	a0, s1, 1
				call	malloc
				mv	s0, a0
				add	a1, s2, s3
				mv	a2, s1
				call	memcpy
				add	a0, s0, s1
				sb	zero, 0(a0)
				mv	a0, s0
				lw	s3, 12(sp)
				lw	s2, 16(sp)
				lw	s1, 20(sp)
				lw	s0, 24(sp)
				lw	ra, 28(sp)
				addi	sp, sp, 32
				ret
			.Lfunc_end9:
				.size	__str_substring, .Lfunc_end9-__str_substring
			                                        # -- End function
				.globl	__str_parseInt          # -- Begin function __str_parseInt
				.p2align	2
				.type	__str_parseInt,@function
			__str_parseInt:                         # @__str_parseInt
			# %bb.0:
				addi	sp, sp, -16
				sw	ra, 12(sp)
				lui	a1, %hi(.L.str)
				addi	a1, a1, %lo(.L.str)
				addi	a2, sp, 8
				call	sscanf
				lw	a0, 8(sp)
				lw	ra, 12(sp)
				addi	sp, sp, 16
				ret
			.Lfunc_end10:
				.size	__str_parseInt, .Lfunc_end10-__str_parseInt
			                                        # -- End function
				.globl	__str_ord               # -- Begin function __str_ord
				.p2align	2
				.type	__str_ord,@function
			__str_ord:                              # @__str_ord
			# %bb.0:
				add	a0, a0, a1
				lbu	a0, 0(a0)
				ret
			.Lfunc_end11:
				.size	__str_ord, .Lfunc_end11-__str_ord
			                                        # -- End function
				.globl	print                   # -- Begin function print
				.p2align	2
				.type	print,@function
			print:                                  # @print
			# %bb.0:
				lui	a1, %hi(.L.str.1)
				addi	a1, a1, %lo(.L.str.1)
				mv	a2, a0
				mv	a0, a1
				mv	a1, a2
				tail	printf
			.Lfunc_end12:
				.size	print, .Lfunc_end12-print
			                                        # -- End function
				.globl	println                 # -- Begin function println
				.p2align	2
				.type	println,@function
			println:                                # @println
			# %bb.0:
				tail	puts
			.Lfunc_end13:
				.size	println, .Lfunc_end13-println
			                                        # -- End function
				.globl	printInt                # -- Begin function printInt
				.p2align	2
				.type	printInt,@function
			printInt:                               # @printInt
			# %bb.0:
				lui	a1, %hi(.L.str)
				addi	a1, a1, %lo(.L.str)
				mv	a2, a0
				mv	a0, a1
				mv	a1, a2
				tail	printf
			.Lfunc_end14:
				.size	printInt, .Lfunc_end14-printInt
			                                        # -- End function
				.globl	printlnInt              # -- Begin function printlnInt
				.p2align	2
				.type	printlnInt,@function
			printlnInt:                             # @printlnInt
			# %bb.0:
				lui	a1, %hi(.L.str.3)
				addi	a1, a1, %lo(.L.str.3)
				mv	a2, a0
				mv	a0, a1
				mv	a1, a2
				tail	printf
			.Lfunc_end15:
				.size	printlnInt, .Lfunc_end15-printlnInt
			                                        # -- End function
				.globl	getString               # -- Begin function getString
				.p2align	2
				.type	getString,@function
			getString:                              # @getString
			# %bb.0:
				addi	sp, sp, -16
				sw	ra, 12(sp)
				sw	s0, 8(sp)
				addi	a0, zero, 256
				call	malloc
				mv	s0, a0
				lui	a0, %hi(.L.str.1)
				addi	a0, a0, %lo(.L.str.1)
				mv	a1, s0
				call	scanf
				mv	a0, s0
				lw	s0, 8(sp)
				lw	ra, 12(sp)
				addi	sp, sp, 16
				ret
			.Lfunc_end16:
				.size	getString, .Lfunc_end16-getString
			                                        # -- End function
				.globl	getInt                  # -- Begin function getInt
				.p2align	2
				.type	getInt,@function
			getInt:                                 # @getInt
			# %bb.0:
				addi	sp, sp, -16
				sw	ra, 12(sp)
				lui	a0, %hi(.L.str)
				addi	a0, a0, %lo(.L.str)
				addi	a1, sp, 8
				call	scanf
				lw	a0, 8(sp)
				lw	ra, 12(sp)
				addi	sp, sp, 16
				ret
			.Lfunc_end17:
				.size	getInt, .Lfunc_end17-getInt
			                                        # -- End function
				.globl	toString                # -- Begin function toString
				.p2align	2
				.type	toString,@function
			toString:                               # @toString
			# %bb.0:
				addi	sp, sp, -16
				sw	ra, 12(sp)
				sw	s0, 8(sp)
				sw	s1, 4(sp)
				mv	s0, a0
				addi	a0, zero, 12
				call	malloc
				mv	s1, a0
				lui	a0, %hi(.L.str)
				addi	a1, a0, %lo(.L.str)
				mv	a0, s1
				mv	a2, s0
				call	sprintf
				mv	a0, s1
				lw	s1, 4(sp)
				lw	s0, 8(sp)
				lw	ra, 12(sp)
				addi	sp, sp, 16
				ret
			.Lfunc_end18:
				.size	toString, .Lfunc_end18-toString
			                                        # -- End function
				.type	STR_BUF_SIZE,@object    # @STR_BUF_SIZE
				.section	.rodata,"a",@progbits
				.globl	STR_BUF_SIZE
				.p2align	2
			STR_BUF_SIZE:
				.word	256                     # 0x100
				.size	STR_BUF_SIZE, 4

				.type	INT_BUF_SIZE,@object    # @INT_BUF_SIZE
				.globl	INT_BUF_SIZE
				.p2align	2
			INT_BUF_SIZE:
				.word	20                      # 0x14
				.size	INT_BUF_SIZE, 4

				.type	.L.str,@object          # @.str
				.section	.rodata.str1.1,"aMS",@progbits,1
			.L.str:
				.asciz	"%d"
				.size	.L.str, 3

				.type	.L.str.1,@object        # @.str.1
			.L.str.1:
				.asciz	"%s"
				.size	.L.str.1, 3

				.type	.L.str.3,@object        # @.str.3
			.L.str.3:
				.asciz	"%d\\n"
				.size	.L.str.3, 4

				.ident	"clang version 10.0.0-4ubuntu1 "
				.section	".note.GNU-stack","",@progbits
				.addrsig

												            """;

	public void printBuiltin() throws FileNotFoundException {
		File builtin = new File("builtin.s");
		PrintStream ps = new PrintStream(builtin);
		ps.print(builtinS);
		ps.close();
	}
}
