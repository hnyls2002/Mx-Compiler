	.text
	.file	"builtin.c"
	.globl	__malloc                # -- Begin function __malloc
	.p2align	2
	.type	__malloc,@function
__malloc:                               # @__malloc
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	sw	s0, 8(sp)
	addi	s0, sp, 16
	sw	a0, -12(s0)
	lw	a0, -12(s0)
	call	malloc
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end0:
	.size	__malloc, .Lfunc_end0-__malloc
                                        # -- End function
	.globl	__str_plus              # -- Begin function __str_plus
	.p2align	2
	.type	__str_plus,@function
__str_plus:                             # @__str_plus
# %bb.0:
	addi	sp, sp, -32
	sw	ra, 28(sp)
	sw	s0, 24(sp)
	addi	s0, sp, 32
	sw	a0, -12(s0)
	sw	a1, -16(s0)
	lw	a0, -12(s0)
	call	strlen
	lw	a1, -16(s0)
	sw	a0, -24(s0)
	mv	a0, a1
	call	strlen
	lw	a1, -24(s0)
	add	a0, a1, a0
	addi	a0, a0, 1
	call	malloc
	sw	a0, -20(s0)
	lw	a0, -20(s0)
	lw	a1, -12(s0)
	call	strcpy
	lw	a1, -20(s0)
	lw	a2, -16(s0)
	sw	a0, -28(s0)
	mv	a0, a1
	mv	a1, a2
	call	strcat
	lw	a1, -20(s0)
	sw	a0, -32(s0)
	mv	a0, a1
	lw	s0, 24(sp)
	lw	ra, 28(sp)
	addi	sp, sp, 32
	ret
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
	sw	s0, 8(sp)
	addi	s0, sp, 16
	sw	a0, -12(s0)
	sw	a1, -16(s0)
	lw	a0, -12(s0)
	lw	a1, -16(s0)
	call	strcmp
	seqz	a0, a0
	lw	s0, 8(sp)
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
	sw	s0, 8(sp)
	addi	s0, sp, 16
	sw	a0, -12(s0)
	sw	a1, -16(s0)
	lw	a0, -12(s0)
	lw	a1, -16(s0)
	call	strcmp
	snez	a0, a0
	lw	s0, 8(sp)
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
	sw	s0, 8(sp)
	addi	s0, sp, 16
	sw	a0, -12(s0)
	sw	a1, -16(s0)
	lw	a0, -12(s0)
	lw	a1, -16(s0)
	call	strcmp
	srli	a0, a0, 31
	lw	s0, 8(sp)
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
	sw	s0, 8(sp)
	addi	s0, sp, 16
	sw	a0, -12(s0)
	sw	a1, -16(s0)
	lw	a0, -12(s0)
	lw	a1, -16(s0)
	call	strcmp
	slti	a0, a0, 1
	lw	s0, 8(sp)
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
	sw	s0, 8(sp)
	addi	s0, sp, 16
	sw	a0, -12(s0)
	sw	a1, -16(s0)
	lw	a0, -12(s0)
	lw	a1, -16(s0)
	call	strcmp
	mv	a1, zero
	slt	a0, a1, a0
	lw	s0, 8(sp)
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
	sw	s0, 8(sp)
	addi	s0, sp, 16
	sw	a0, -12(s0)
	sw	a1, -16(s0)
	lw	a0, -12(s0)
	lw	a1, -16(s0)
	call	strcmp
	not	a0, a0
	srli	a0, a0, 31
	lw	s0, 8(sp)
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
	addi	sp, sp, -16
	sw	ra, 12(sp)
	sw	s0, 8(sp)
	addi	s0, sp, 16
	sw	a0, -12(s0)
	lw	a0, -12(s0)
	call	strlen
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
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
	addi	s0, sp, 32
	sw	a0, -12(s0)
	sw	a1, -16(s0)
	sw	a2, -20(s0)
	lw	a0, -20(s0)
	lw	a1, -16(s0)
	sub	a0, a0, a1
	addi	a0, a0, 1
	call	malloc
	sw	a0, -24(s0)
	lw	a0, -24(s0)
	lw	a1, -12(s0)
	lw	a2, -16(s0)
	add	a1, a1, a2
	lw	a3, -20(s0)
	sub	a2, a3, a2
	call	memcpy
	lw	a1, -24(s0)
	lw	a2, -20(s0)
	lw	a3, -16(s0)
	sub	a2, a2, a3
	add	a1, a1, a2
	mv	a2, zero
	sb	a2, 0(a1)
	lw	a1, -24(s0)
	sw	a0, -28(s0)
	mv	a0, a1
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
	addi	sp, sp, -32
	sw	ra, 28(sp)
	sw	s0, 24(sp)
	addi	s0, sp, 32
	sw	a0, -12(s0)
	lw	a0, -12(s0)
	lui	a1, %hi(.L.str)
	addi	a1, a1, %lo(.L.str)
	addi	a2, s0, -16
	call	sscanf
	lw	a1, -16(s0)
	sw	a0, -20(s0)
	mv	a0, a1
	lw	s0, 24(sp)
	lw	ra, 28(sp)
	addi	sp, sp, 32
	ret
.Lfunc_end10:
	.size	__str_parseInt, .Lfunc_end10-__str_parseInt
                                        # -- End function
	.globl	__str_ord               # -- Begin function __str_ord
	.p2align	2
	.type	__str_ord,@function
__str_ord:                              # @__str_ord
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	sw	s0, 8(sp)
	addi	s0, sp, 16
	sw	a0, -12(s0)
	sw	a1, -16(s0)
	lw	a0, -12(s0)
	lw	a1, -16(s0)
	add	a0, a0, a1
	lbu	a0, 0(a0)
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end11:
	.size	__str_ord, .Lfunc_end11-__str_ord
                                        # -- End function
	.globl	print                   # -- Begin function print
	.p2align	2
	.type	print,@function
print:                                  # @print
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	sw	s0, 8(sp)
	addi	s0, sp, 16
	sw	a0, -12(s0)
	lw	a1, -12(s0)
	lui	a0, %hi(.L.str.1)
	addi	a0, a0, %lo(.L.str.1)
	call	printf
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end12:
	.size	print, .Lfunc_end12-print
                                        # -- End function
	.globl	println                 # -- Begin function println
	.p2align	2
	.type	println,@function
println:                                # @println
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	sw	s0, 8(sp)
	addi	s0, sp, 16
	sw	a0, -12(s0)
	lw	a1, -12(s0)
	lui	a0, %hi(.L.str.2)
	addi	a0, a0, %lo(.L.str.2)
	call	printf
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end13:
	.size	println, .Lfunc_end13-println
                                        # -- End function
	.globl	printInt                # -- Begin function printInt
	.p2align	2
	.type	printInt,@function
printInt:                               # @printInt
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	sw	s0, 8(sp)
	addi	s0, sp, 16
	sw	a0, -12(s0)
	lw	a1, -12(s0)
	lui	a0, %hi(.L.str)
	addi	a0, a0, %lo(.L.str)
	call	printf
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end14:
	.size	printInt, .Lfunc_end14-printInt
                                        # -- End function
	.globl	printlnInt              # -- Begin function printlnInt
	.p2align	2
	.type	printlnInt,@function
printlnInt:                             # @printlnInt
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	sw	s0, 8(sp)
	addi	s0, sp, 16
	sw	a0, -12(s0)
	lw	a1, -12(s0)
	lui	a0, %hi(.L.str.3)
	addi	a0, a0, %lo(.L.str.3)
	call	printf
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
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
	addi	s0, sp, 16
	addi	a0, zero, 256
	call	malloc
	sw	a0, -12(s0)
	lw	a1, -12(s0)
	lui	a0, %hi(.L.str.1)
	addi	a0, a0, %lo(.L.str.1)
	call	scanf
	lw	a1, -12(s0)
	sw	a0, -16(s0)
	mv	a0, a1
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
	sw	s0, 8(sp)
	addi	s0, sp, 16
	lui	a0, %hi(.L.str)
	addi	a0, a0, %lo(.L.str)
	addi	a1, s0, -12
	call	scanf
	lw	a1, -12(s0)
	sw	a0, -16(s0)
	mv	a0, a1
	lw	s0, 8(sp)
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
	addi	sp, sp, -32
	sw	ra, 28(sp)
	sw	s0, 24(sp)
	addi	s0, sp, 32
	sw	a0, -12(s0)
	addi	a0, zero, 12
	call	malloc
	sw	a0, -16(s0)
	lw	a0, -16(s0)
	lw	a2, -12(s0)
	lui	a1, %hi(.L.str)
	addi	a1, a1, %lo(.L.str)
	call	sprintf
	lw	a1, -16(s0)
	sw	a0, -20(s0)
	mv	a0, a1
	lw	s0, 24(sp)
	lw	ra, 28(sp)
	addi	sp, sp, 32
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

	.type	.L.str.2,@object        # @.str.2
.L.str.2:
	.asciz	"%s\n"
	.size	.L.str.2, 4

	.type	.L.str.3,@object        # @.str.3
.L.str.3:
	.asciz	"%d\n"
	.size	.L.str.3, 4

	.ident	"clang version 10.0.0-4ubuntu1 "
	.section	".note.GNU-stack","",@progbits
	.addrsig
	.addrsig_sym malloc
	.addrsig_sym strlen
	.addrsig_sym strcpy
	.addrsig_sym strcat
	.addrsig_sym strcmp
	.addrsig_sym sscanf
	.addrsig_sym printf
	.addrsig_sym scanf
	.addrsig_sym sprintf
